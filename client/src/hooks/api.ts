import axios, { AxiosError, type AxiosRequestConfig } from "axios";
import type { ChatMessage } from "../types/chat-message";
import type { ChatRoom } from "../types/chat-room";

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
    if (error.response && error.response.status === 401) {
      // 401 에러 발생 시 /index 로 이동
      window.location.href = "/";
    }
    return Promise.reject(error); // 에러는 그대로 전달
  }
);

export const getChatMessages = async (): Promise<ChatMessage[]> => {
  const response = await axios.get(
    `${import.meta.env.VITE_API_BASE_URL}/cars`,
    getAxiosConfig()
  );
  return response.data._embedded.cars;
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
