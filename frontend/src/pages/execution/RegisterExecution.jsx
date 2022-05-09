import React, {useEffect, useState} from 'react';
import { upload } from '../../utils/stockExecution'

export const RegisterExecution = () => {
    const [multipartFile, setMultipartFile] = useState("")
    const submit = async (event) => {
        // formのデフォルト挙動をキャンセル
        event.preventDefault()
        if (multipartFile === null || multipartFile === "" || multipartFile === undefined){
            return;
        }
        console.log(multipartFile)
        const res = await upload(multipartFile).then(res => {
                                    const {data, status} = res;
                                    console.log(data)
                                }).catch(res => {
                                    const {data, status} = res;
                                    console.error(status)
                                    console.error(data)
                                })
    }

    return (
        <div className={"form-group"}>
            <h2 className={"col-md-6 pt-3"}>約定株式登録</h2>
            <form className={"form"} onSubmit={submit}>
                <input className={"btn"} type="file" onChange = {e => setMultipartFile(e.target.files[0])}></input>
        　　    <button className={"btn btn-outline-primary"} type="submit" name="submit" value="submit">登録</button>
            </form>
            <hr/>
            <div ></div>                                                                                                     
        </div>
    )
}