import React, { useEffect } from "react";
import styled from "styled-components";

import hljs from "highlight.js";
//highlight 테마 스타일
import "highlight.js/styles/xcode.css";

import "quill/dist/quill.snow.css";
import { useQuill } from "react-quilljs";

const TextEditBox = styled.div`
    width: 100%;
    .ql-container {
        height: 220px;
        margin-bottom: 11px;
    }
`;

const TextEdit = ({ setContent }) => {
    //입력된 DOM값
    // 부모 컴포넌트에게 줄 것 -> const [content, setContent] = useState("");
    // content에 DOM 데이터가 문자열로 담긴다.
    // setContent를 props로 TextEdit에 보내줘서 DOM 데이터를 받아온다.
    hljs.configure({
        //하이라이트 되는 언어
        languages: ["javascript", "ruby", "python", "rust", "html, xml"],
    });
    const theme = "snow";
    const modules = {
        toolbar: [
            [
                { header: [1, 2, 3, 4, 5, 6, false] },
                "bold",
                "italic",
                "code-block",
            ],
            [{ list: "ordered" }, { list: "bullet" }],
            ["blockquote", { script: "sub" }, { script: "super" }],
        ],
        syntax: {
            highlight: (text) => hljs.highlightAuto(text).value,
        },
    };
    const formats = [
        "header",
        "bold",
        "italic",
        "code-block",
        "list",
        "blockquote",
        "script",
    ];
    const placeholder = "입력해 주세요...";

    const { quill, quillRef } = useQuill({
        theme,
        modules,
        formats,
        placeholder,
    });

    useEffect(() => {
        if (quill) {
            quill.on("text-change", () => {
                setContent(quill.root.innerHTML);
            });
        }
    }, [quill]);
    return (
        <TextEditBox>
            <div ref={quillRef} />
        </TextEditBox>
    );
};

export default TextEdit;
