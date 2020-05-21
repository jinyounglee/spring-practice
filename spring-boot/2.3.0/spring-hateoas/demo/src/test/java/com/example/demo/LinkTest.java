package com.example.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.*;

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
    @DisplayName("IANA link relations resource 테스트")
    public void resourceIANATest() throws Exception {
        Link link = Link.of("/some-resource", IanaLinkRelations.NEXT);
        assertThat(link.getRel()).isEqualTo(LinkRelation.of("next"));
        assertThat(IanaLinkRelations.isIanaRel(link.getRel())).isTrue();
    }

    @Test
    @DisplayName("URI 템플릿 사용 테스트")
    public void uriTemplateTest() {
        UriTemplate template = UriTemplate.of("/{segment}/something")
                .with(new TemplateVariable("parameter", TemplateVariable.VariableType.REQUEST_PARAM));

        Map<String, Object> values = new HashMap<>();
        values.put("segment", "path");
        values.put("parameter", 42);

        assertThat(template.toString()).isEqualTo("/{segment}/something{?parameter}");
        assertThat(template.expand(values).toString()).isEqualTo("/path/something?parameter=42");
    }

    @Test
    @DisplayName("Affordance 테스트")
    public void affordanceTest() throws Exception {

    }
}
