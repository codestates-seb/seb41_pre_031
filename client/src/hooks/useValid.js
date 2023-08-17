import { useEffect, useState } from "react";

export default function useValid(changeValue){
    //표시할 문구
    const [validText, setValidText] = useState({
        emailValid: '',
        passwordValid: ''
    });
    //유효성 검사
    const [isValid, setIsValid] = useState({
        isEmail: false,
        isPassword: false
    });

    useEffect(() => {
        const exp = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
        if(!exp.test(changeValue.email)){
            setValidText({...validText, emailValid: '이메일을 확인해주세요'});
            setIsValid({...isValid, isEmail: false});
        } else {
            setValidText('');
            setIsValid({...isValid, isEmail: true});
        }
    }, [changeValue.email]);

    useEffect(() => {
        const exp = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
        if(!exp.test(changeValue.password)){
            setValidText({...validText, passwordValid: '비밀번호를 확인해주세요'});
            setIsValid({...isValid, isPassword: false});
        } else {
            setValidText('');
            setIsValid({...isValid, isPassword: true});
        }
    }, [changeValue.password]);

    return {validText, isValid}
}