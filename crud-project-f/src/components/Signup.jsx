import {useState} from "react"
import axios from "axios"

function Signup() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [nickname, setNickname] = useState("");

  const handelLogin = async () => {
    try {
      const response = await axios.post("http://localhost:8080/user/join", {
        username: username,
        password: password,
        nickname: nickname
      })

      console.log("서버 응답:", response.data);
      alert("로그인 성공!");

    } catch (error) {
      console.error("로그인 실패:", error);
      alert("로그인 실패 ㅠㅠ");
    }
    }
  

  return (
    <div>
      <input
        type="text"
        placeholder="아이디"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
      />
        <input
        type="password"
        placeholder="비밀번호"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
        <input
        type="nickname"
        placeholder="닉네임"
        value={nickname}
        onChange={(e) => setNickname(e.target.value)}
      />
      <button onClick={handelLogin}>회원가입</button>
    </div>
  )
}

export default Signup



