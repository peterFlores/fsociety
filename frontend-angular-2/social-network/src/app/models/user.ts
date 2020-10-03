import { Response } from "./response";
import { UserItem } from "./user_item";

  export interface User extends Response{
   
    data: UserItem;
}
