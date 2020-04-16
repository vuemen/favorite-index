package com.shun.favoriteindex.user.notify.impl;

import com.shun.favoriteindex.user.entity.User;
import com.shun.favoriteindex.user.notify.FacadeUserRegisterNotifyService;
import com.shun.favoriteindex.user.notify.IUserRegisterNotify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacadeUserRegisterNotifyServiceImpl implements FacadeUserRegisterNotifyService {

    @Autowired
    private List<IUserRegisterNotify> notifyList;

    @Override
    public void successNotify(User user) {
        if (notifyList == null || notifyList.size() <= 0) {
            return;
        }
        for (IUserRegisterNotify notify : notifyList) {
            notify.successNotify(user);
        }
    }
}
