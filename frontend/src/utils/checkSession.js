import config from 'AppConfig';
import axios from "axios";

/** Cookieが取得できる場合がTrue*/
export const checkSession = async () => {
    const status = await connectToCheckAPI();
    console.log("HTTP Status => " + status);
    if (status === 200){
        return true
    }
    return false;
}

const connectToCheckAPI = async () => {
    const check = await axios.create({
        baseURL: config.WEB_API_URL,
        validateStatus(status) {
            return status >= 200 && status < 500
        }}).post("/api/v1/system/check-session", {}).then(res => res.status).catch(console.error).catch(res => 500);
    return check
}