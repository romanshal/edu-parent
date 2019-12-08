import {Comment} from "./comment";

export class Post {
  id: number;
  description: string;
  tags: string;
  userId: string;
  countLike: number;
  file: File;
  filePath: string;
  login: string;
  uiComments: Comment[];
}
