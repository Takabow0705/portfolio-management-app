import axios from "axios";
import config from 'AppConfig';

/**
 * 約定ファイルをサーバにアップロードする関数
 * @param {} multipartFile 
 */
export const upload = async (multipartFile) => {
    const baseUri = config.WEB_API_URL
    const api = '/api/v1/app/portfolio/stock-execution/upload'
    const httpHeader = {
        baseURL: baseUri,
        headers: {
            'Content-Type': 'multipart/form-data',
        },
        responseType: 'json'
    };
    const params = new FormData();
    params.append('multipartFile', multipartFile)
    console.log("send multipartFile. Datetime : ", + Date.now())
    return await axios.create(httpHeader).post(api,params)
}
