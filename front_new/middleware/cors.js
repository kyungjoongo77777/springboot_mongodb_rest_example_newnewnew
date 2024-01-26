// middleware/cors.js

export default function ({ app, req, res }) {
    // CORS 허용을 위한 헤더 설정
    res.setHeader('Access-Control-Allow-Credentials', true);
    res.setHeader('Access-Control-Allow-Origin', '*'); // 특정 도메인을 허용하려면 해당 도메인을 입력

    // 요청 메서드 및 헤더 설정
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, DELETE');
    res.setHeader('Access-Control-Allow-Headers', 'Origin, Content-Type, Accept');

    // preflight 요청에 대한 처리
    if (req.method === 'OPTIONS') {
        return res.end();
    }
}