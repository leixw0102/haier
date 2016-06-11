package com.haier.service;

import com.haier.domain.User;

/**
 * Created by bright on 16-6-10.
 */
public interface UserService {
    public User addNewUser(User user) throws Exception;
    public User isExistsUser(User user) throws Exception;
    public void clearAccessToken(User user) throws Exception;
    public Boolean IsValidAccess(String userKey, int u, String t, String m);
}