package org.rizki.mufrizal.gateway.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZuulRoute implements Serializable {

    @Id
    @Column(name = "id", length = 36)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @Column(name = "path")
    private String path;

    @Column(name = "service_id", length = 100)
    private String serviceId;

    @Column(name = "url")
    private String url;

    @Column(name = "strip_prefix")
    private String stripPrefix;

    @Column(name = "retryable")
    private String retryable;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "sensitive_headers_list")
    private String sensitiveheadersList;

}