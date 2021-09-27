import axios from "axios";
import config from 'AppConfig';
/**
 * ログイン・ログアウト関連のAPIを叩くクライアント
 */
export class LoginClient{
    /** APIエンドポインtののbaseURLを設定。*/
    constructor() {
        this.baseURL = config.WEB_API_URL;
    }

    async sendLoginRequest(username, password){
        const client = axios.create({
            baseURL: this.baseURL,
            headers: {
                'Content-Type': 'application/json',
            },
            responseType: 'json'
        })
        const request = {
            username: username,
            password: password
        }
        return await client.post('api/login', request)
    }
}