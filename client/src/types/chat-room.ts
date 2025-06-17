import type { ChatMessage } from "./chat-message";

export type ChatRoom = {
  id: number;
  name: string;
  createdBy: string;
  createdAt: string;
  lastMessageInfo: ChatMessage | null;
};
