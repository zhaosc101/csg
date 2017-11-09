package com.zsc.csg.web.security.token;

import com.zsc.csg.core.util.UUIDUtil;

/**
 * token 实体
 */
public class Token {

    private String tokenId;

    private String userName;

    private String uuid;

    private final long createTime = System.currentTimeMillis();

    public Token() {
        this.tokenId = UUIDUtil.randomUUID();
        this.userName = "";
    }

    public Token(String userName) {
        this.tokenId = UUIDUtil.randomUUID();
        this.userName = userName;
    }

    /**
     * @return the tokenId
     */
    public String getTokenId() {
        return tokenId;
    }

    /**
     * @param tokenId the tokenId to set
     */
    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @return the createTime
     */
    public long getCreateTime() {
        return createTime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + tokenId.hashCode();
        result = prime * result + userName.hashCode();
        return result;
    }
}
