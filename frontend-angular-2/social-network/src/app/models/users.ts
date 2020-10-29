import { Response } from "./response";
import { UserItem } from "./user_item";

export interface Users extends Response {
   
    data: UserItem[];
}
