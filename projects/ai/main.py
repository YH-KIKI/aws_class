from fastapi import FastAPI, Form

app = FastAPI()

@app.get("/")
async def index():
    return {"message": "Hello FastAPI"}

@app.post("/test")
async def testPost(msg=Form(...)):
    print(msg)
    return {"msg" : "FastAPI"}

if __name__ == "__main__":
    import uvicorn
    uvicorn.run("main:app", port=8000, reload=True)