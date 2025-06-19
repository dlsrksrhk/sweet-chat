export type SessionUserInfo = {
  id: number;
  loginId: string;
  userName: string;
};

export type UserSignUpRequest = {
  loginId: string;
  userName: string;
  password: string;
  email: string;
};
