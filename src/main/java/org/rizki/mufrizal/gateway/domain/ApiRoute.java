package org.rizki.mufrizal.gateway.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_zuul_route")
@Getter
@Setter
public class ApiRoute implements Serializable {

    @Id
    @Column(name = "id", length = 36)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @Column(name = "path")
    private String path;

    @Column(name = "name")
    private String name;

    @Column(name = "version")
    private String version;

    @Column(name = "url")
    private String url;

    @Column(name = "strip_prefix")
    private Boolean stripPrefix;

    @Column(name = "enabled")
    private Boolean enabled;

}