package com.example.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LinkTest {

    @Test
    @DisplayName("link 테스트 ")
    public void linkTest() throws Exception {
        Link link = Link.of("/{segment}/something{?parameter}");
        assertThat(link.isTemplated()).isTrue();
        assertThat(link.getVariableNames()).contains("segment", "parameter");

        Map<String, Object> values = new HashMap<>();
        values.put("segment", "path");
        values.put("parameter", 42);


        assertThat(link.expand(values).getHref()).isEqualTo("/path/something?parameter=42");
    }

    @Test
    @DisplayName("resource 테스트")
    public void resourceTest() throws Exception {
        Link link = Link.of("/some-resource", IanaLinkRelations.NEXT);
        assertThat(link.getRel()).isEqualTo(LinkRelation.of("next"));
        assertThat(IanaLinkRelations.isIanaRel(link.getRel())).isTrue();
    }

    @Test
    @DisplayName("Affordance 테스트")
    public void affordanceTest() throws Exception {

    }
}
