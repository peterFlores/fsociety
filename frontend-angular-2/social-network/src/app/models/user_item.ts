import { Post } from "./post";

export interface UserItem {
    idUser?: number;
    userName?: string;
    userBirthDate?: string;
    userGender?: string;
    userNickname?: string;
    userMail?: string;
    userPassword?: string;
    userImage?: string;
    userRole?: string;
    userCreatedAt?: string;
    userStatus?: string;
    posts?: Post[];
}