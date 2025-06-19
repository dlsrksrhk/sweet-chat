import axios, { AxiosError, type AxiosRequestConfig } from "axios";
import type { ChatMessage } from "../types/chat-message";
import type { ChatRoom } from "../types/chat-room";
import type { SessionUserInfo, UserSignUpRequest } from "../types/user";

const getAxiosConfig = (): AxiosRequestConfig => {
  const token = sessionStorage.getItem("jwt");

  return {
    headers: {
      Authorization: `Bearer ${token}`,
      "Content-Type": "application/json",
    },
  };
};

axios.interceptors.response.use(
  (response) => response, // 정상 응답은 그대로 반환
  (error: AxiosError) => {
    if (
      error.response &&
      (error.response.status === 401 || error.response.status === 403)
    ) {
      window.location.href = "/login";
    }
    return Promise.reject(error);
  }
);

export const getSessionUserInfo = async (): Promise<SessionUserInfo> => {
  const response = await axios.get(
    `${import.meta.env.VITE_API_BASE_URL}/api/session/userinfo`,
    getAxiosConfig()
  );

  return response.data;
};

export const getChatMessages = async (
  roomId: number
): Promise<ChatMessage[]> => {
  const response = await axios.get(
    `${import.meta.env.VITE_API_BASE_URL}/api/chatrooms/${roomId}/messages`,
    getAxiosConfig()
  );
  return response.data;
};

export const createChatRoom = async (roomName: string): Promise<ChatRoom> => {
  const response = await axios.post(
    `${import.meta.env.VITE_API_BASE_URL}/api/chatrooms`,
    { name: roomName },
    getAxiosConfig()
  );

  return response.data;
};

export const getChatRoomList = async (): Promise<ChatRoom[]> => {
  const response = await axios.get(
    `${import.meta.env.VITE_API_BASE_URL}/api/chatrooms`,
    getAxiosConfig()
  );

  return response.data;
};

export const userSingUp = async (
  signUpRequest: UserSignUpRequest
): Promise<ChatRoom> => {
  const response = await axios.post(
    `${import.meta.env.VITE_API_BASE_URL}/api/user/signup`,
    signUpRequest
  );

  return response.data;
};
