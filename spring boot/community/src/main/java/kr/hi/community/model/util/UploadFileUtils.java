package kr.hi.community.model.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;


public class UploadFileUtils {
	
	/* 첨부파일을 업로드하는 메서드
	 * @Param uploadPath 업로드할 폴더 위치(서버)
	 * @Param file 실제 첨부 파일
	 * @return 업로드한 경로
	 * @throws Exception
	 * */
	
	public static String uploadFile(String uploadPath, MultipartFile file) throws Exception {
	    // 1. 기본적인 Null 체크 및 파일 존재 여부 확인 (방어 코드)
		// 처음 2개 if는 매개변수 체크
	    if (file == null || file.isEmpty()) {
	        throw new IllegalArgumentException("업로드할 파일이 존재하지 않습니다.");
	    }
	    // 경로가 맞는지 확인 
	    if (uploadPath == null || uploadPath.trim().isEmpty()) {
	        throw new IllegalArgumentException("업로드 경로가 설정되지 않았습니다.");
	    }
	
	    String originalName = file.getOriginalFilename();
	    // 화면에서 첨부파일을 선택하지 않으면 file 객체가 생성되고, 
	    // 파일명이 없는 파일명이 전송되는 걸 처리하는 작업
	    if (originalName == null || originalName.isEmpty()) {
	        throw new IllegalArgumentException("잘못된 첨부파일입니다.");
	    }
	    // 2. 고유 파일명 생성
		// 같은 파일을 서버에 업로드 하더라도 덮어쓰기가 일어나지 않도록
		// 업로드하기전 파일명 앞에 중복되지 않은 UUID를 추가해서 파일명 중복을 방지.
	    String uuid = UUID.randomUUID().toString();
	    String savedName = uuid + "_" + originalName;
	
	    // 3. 날짜별 경로 계산 및 폴더 생성 (/2026/01/08)
	    
	    String datePath = calcPath(uploadPath);
	
	    // 4. 파일 저장 (경로 + 날짜경로 + 파일명)
	    // C:/upload/2026/01/08/uuid_파일명
	    Path targetPath = Paths.get(uploadPath, datePath, savedName);
	    // InputStream을 사용하여 메모리 효율적으로 복사
	    // 실제 파일 복사
	    Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
	
	    // 5. DB 저장용 경로 반환 (역슬래시를 슬래시로 통일)
	    // 운영체재마다 폴더를 구분하는 기호가 다름. 윈도우는 \, 리눅스는 /
	    // 경로에 있는 폴더 구분 기호를 전부 /로 변경하여 db에 저장
	    return Paths.get(datePath, savedName).toString().replace(File.separatorChar, '/');
	}

	private static String calcPath(String uploadPath) throws IOException {
	    // LocalDate를 사용하여 현재 날짜 경로 생성
		// 현재 26년 1월 8일이면 2026/01/08이 생성
	    String datePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
	    
	    // 서버 경로에 2026/01/08경로가 없으면 실제 폴더를 만들어야 해서 Path 객체로 변환 
	    Path fullPath = Paths.get(uploadPath, datePath);
	    
	    // 폴더가 없으면 상위 폴더까지 한 번에 생성
	    // 서버 경로에 2026/01/08폴더가 없을 경우
	    // 중간 폴더들이 없는 경우 중간 폴더 포함하여 모든 폴더를 추가
	    // ex) 서버 경로에 2026폴더가 없으면 2026년 폴더를 생성한 후,
	    //	   2026/01폴더가 없으면 2026/01폴더를 생성 하고 
	    //     최종 2026/01/08를 생성
	    if (!Files.exists(fullPath)) {
	        Files.createDirectories(fullPath);
	    }
	
	    return datePath;
	}
	
	public static void deleteFile(String uploadPath, String fileName) {
	    // DB에 저장된 '/' 경로를 운영체제에 맞는 구분자로 변환
	    String pathName = fileName.replace('/', File.separatorChar);
	    Path file = Paths.get(uploadPath, pathName);
	
	    try {
	        // 파일이 존재할 때만 삭제
	        Files.deleteIfExists(file);
	    } catch (IOException e) {
	        System.err.println("파일 삭제 실패: " + e.getMessage());
	    }
	}

}