import { ref, computed } from "vue";
import { defineStore } from "pinia";
import router from "@/router";
import axios from "axios";

const REST_API_USER = `http://localhost:8080/api-user/user`;
const REST_API_FAVORITE = "http://localhost:8080/api-favorite/favorite";

axios.defaults.withCredentials = true;
//.withCredentials의 기본값을 true값으로 변경->CORS 요청 허용, 쿠키값을 전달 할 수 있게 됨.
// 전역 설정
export const useUserStore = defineStore("user", () => {
  const accessToken = ref(sessionStorage.getItem("access-token"));
  const loginUserId = ref(sessionStorage.getItem("loginUserId"));

  const userLogin = async (id, password) => {
    try {
      const res = await axios.post(`${REST_API_USER}/login`, { id, password });
      const token = res.data["access-token"];
      sessionStorage.setItem("access-token", token);
      accessToken.value = token;

      const decoded = JSON.parse(atob(token.split(".")[1]));
      const userId = decoded.id;
      sessionStorage.setItem("loginUserId", userId);
      loginUserId.value = userId;

      await getUserInfo(userId);

      router.push("/");
    } catch (err) {
      alert(
        "아이디 또는 비밀번호를 잘못 입력했습니다. 입력하신 내용을 다시 확인해주세요."
      );
    }
  };

  const loginUserInfo = ref({});

  const getUserInfo = async (id) => {
    try {
      const res = await axios.get(`${REST_API_USER}/${id}`, {
        headers: { "access-token": accessToken.value },
      });
      loginUserInfo.value = res.data;
    } catch (err) {
      // console.error(err);
    }
  };

  const createUser = function (user, file) {
    // console.log(user)
    const formData = new FormData();
    formData.append(
      "user",
      new Blob([JSON.stringify(user)], { type: "application/json" })
    );
    formData.append("file", file);
    axios
      .post(`${REST_API_USER}`, formData, {
        headers: {
          "access-token": accessToken.value,
          "Content-Type": "multipart/form-data",
        },
      })
      .then((res) => {
        sessionStorage.removeItem("access-token");
        sessionStorage.removeItem("loginUserId");
        alert("회원가입에 성공하셨습니다. 로그인 해주세요.");
        router.push({ name: "enter" });
      })
      .catch((err) => {
        alert("회원가입에 실패하셨습니다.");
      });
  };

  const logout = () => {
    sessionStorage.removeItem("access-token");
    sessionStorage.removeItem("loginUserId");
    router.push({ name: "enter" });
  };

  const updateUser = function (user, file) {
    const formData = new FormData();
    formData.append(
      "user",
      new Blob([JSON.stringify(user)], { type: "application/json" })
    );
    formData.append("file", file);
    axios
      .put(`${REST_API_USER}`, formData, {
        headers: {
          "access-token": accessToken.value,
          "Content-Type": "multipart/form-data",
        },
      })
      .then((res) => {
        alert("개인정보를 수정하였습니다.");
        router.push({ name: "userPage" });
      })
      .catch((err) => {
        alert("개인정보 수정에 실패했습니다.");
      });
  };

  const updateFavorite = (nowStatus, userId, storeId) => {
    if (nowStatus === 0) {
      return axios.post(
        `${REST_API_FAVORITE}`,
        {
          userId: userId,
          storeId: storeId,
        },
        {
          headers: {
            "access-token": accessToken.value,
          },
        }
      );
    } else {
      return axios.delete(`${REST_API_FAVORITE}/${userId}/${storeId}`, {
        headers: {
          "access-token": accessToken.value,
        },
      });
    }
  };

  const favoriteStoreList = ref({});
  const getFavoriteStoreList = function (id) {
    axios
      .get(`${REST_API_FAVORITE}/${id}`, {
        headers: { "access-token": accessToken.value },
      })
      .then((res) => {
        // console.log(res);
        favoriteStoreList.value = res.data;
      })
      .catch(() => {});
  };

  return {
    userLogin,
    loginUserId,
    loginUserInfo,
    getUserInfo,
    updateFavorite,
    createUser,
    logout,
    updateUser,
    getFavoriteStoreList,
    favoriteStoreList,
  };
});
