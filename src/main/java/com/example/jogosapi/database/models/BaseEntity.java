package com.example.jogosapi.database.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {

    @Column(name = "create_by", updatable = false)
    private String createBy;

    @Column(name = "update_by")
    private String updateBy;


    @Column(name = "create_at", updatable = false, nullable = false)
    private LocalDateTime createAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @PrePersist
    public void prePersist() {
        createAt = LocalDateTime.now();
        updateAt = LocalDateTime.now();
        // quando for por o SpringSecurity voltar aqui
    }



    @PreUpdate
    public void preUpdate() {
        updateAt = LocalDateTime.now();
        // quando for por o SpringSecurity voltar aqui
    }

    public void SoftDelete(){
        this.deletedAt = LocalDateTime.now();
    }

    public boolean isDeleted() {
        return this.deletedAt != null;
    }
}
