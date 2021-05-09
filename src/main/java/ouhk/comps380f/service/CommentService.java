package ouhk.comps380f.service;

import ouhk.comps380f.dao.Comment;

public interface CommentService {

    void createComment(Comment comment, String username, int itemid);

    void deleteComment(int id, String username);

}
