# 네이버 뉴스를 가져오는 기능들이 모음

import requests
from bs4 import BeautifulSoup
import pandas as pd
import time

def get_naver_news_list(keyword : str, start : int = 1):
	url = f'https://search.naver.com/search.naver'
	params = {
		'where' : 'news', 
		'query' : keyword,
		'start' : start,
	}
	headers = {
		'User-Agent' : 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/146.0.0.0 Safari/537.36',
		'Referer' : 'https://www.naver.com/'
	}
	# 뉴스 검색 화면을 요청
	response = requests.get(url, params=params, headers=headers)
	try:
		# 연결 성공이 아니면 예외 발생
		response.raise_for_status()
		# Beautifulsoup 객체 생성
		soup = BeautifulSoup(response.text, 'lxml')
		# 뉴스 박스들
		links_divs = soup.select('.n8_DDlCjHxLYRO50CY50')
		hrefs, titles = [], []
		# 반복문으로 제목과 링크를 추출하여 리스트에 담음
		for link_div in links_divs:
			
			# 기사 제목링크 => 여기에서 기사 제목 추출
			title_link = link_div.select_one('.XEtVZ4N7DI2Pv29xe7f3')

			# 실제 기사 네이버 링크 => 여기에서 기사 링크 추출
			href_link = link_div.select_one('.RQNKk8QZaQZble3gmgEj [data-heatmap-target=".nav"]')

			# 리스트에 제목과 링크를 추가 
			hrefs.append(href_link.get("href") if href_link else None)
			titles.append(title_link.text)
		return pd.DataFrame({
			'title': titles,
			'href' : hrefs,
		})
	except Exception as e:
		print(f"예외 발생 : {e}")
		return None

def save_nl(df:pd.DataFrame, keyword:str, start:int = 1):
	# 데이터프레임이 없거나 비어 있으면
	if df is None or df.empty:
		return
	df.to_csv(f'naver_nl_{keyword}_{start//10 + 1}.csv', encoding="utf-8-sig", index=False)	

def get_naver_news_article(url):
	"""네이버 기사에서 내용을 추출하여 반환"""

	headers = {
		'User-Agent' : 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/146.0.0.0 Safari/537.36',
		'Referer' : 'https://www.naver.com/'
	}

	response = requests.get(url, headers=headers)

	try:
		response.raise_for_status()
		
	except Exception as e:
		print(f"예외 발생 {e}")

	soup = BeautifulSoup(response.text, 'lxml')

	article = soup.select_one("#dic_area")

	del_list = [
		# 기사 중간에 이미지가 있는 박스 => 
		# 박스 안에 이미지말고 이미지 설명이라든지 숨겨진 글자가 있을 수 있어서
		'.end_photo_org', 
		# 기사 앞 요약
		'.media_end_summary'
	] # 삭제하고 싶은 요소들
	
	for del_sel in del_list:
		summaries = soup.select(del_sel) 
		for summary in summaries:
			# 원하는 요소를 제거
			summary.decompose()

	return article.text

# 모듈 테스트
if __name__ == "__main__":
	start = 1
	keyword = 'ai'
	nl_df = get_naver_news_list(keyword, start)
	for href in nl_df['href']:
		print(get_naver_news_article(href))
	# save_nl(nl_df, keyword, start)