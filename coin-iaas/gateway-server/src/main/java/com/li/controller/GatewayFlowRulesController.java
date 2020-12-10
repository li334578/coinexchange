package com.li.controller;

import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @author : LiWenBo
 * @program : coinexchange
 * @description :
 * @date : 2020-12-10 21:21:39
 */
@RestController
public class GatewayFlowRulesController {

    /**
     * 获取当前系统的限流策略
     */
    @GetMapping("/gw/flow/rules")
    public Set<GatewayFlowRule> getCurrentGatewayFlowRules(){
        return GatewayRuleManager.getRules();
    }

    /**
     * 获取自定义api分组
     */
    @GetMapping("/gw/api/groups")
    public Set<ApiDefinition> getApiGroups(){
        return GatewayApiDefinitionManager.getApiDefinitions();
    }
}
