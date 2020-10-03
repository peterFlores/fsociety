export interface Post {
    id: number;
    content: string;
    imagePath: string;
    createdAt: Date;
    linkedPost: Post[];
    linkedPosts: Post[];
}
