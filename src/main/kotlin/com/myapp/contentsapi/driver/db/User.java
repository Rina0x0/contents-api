package com.myapp.contentsapi.driver.db;

import lombok.Data;

@Data
// 本当はこのクラスをEntityにするが
// このAPIはひとまず仮プロジェクトなのでクラスだけ用意しておく
public class User {
    public Integer userId;
    public Integer contractId;

    public User() {}

    public User(Integer userId, Integer contractId) {
        this.userId = userId;
        this.contractId = contractId;
    }
}
