export type ChatMessage = {
  id: number;
  roomId: number;
  senderLoginId: string;
  senderUserName: string;
  content: string;
  timestamp: string;
};
