/*
 * Copyright 2012 astamuse company,Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package com.astamuse.asta4d.web;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.astamuse.asta4d.Configuration;
import com.astamuse.asta4d.interceptor.PageInterceptor;
import com.astamuse.asta4d.web.dispatch.AntPathRuleExtractor;
import com.astamuse.asta4d.web.dispatch.DefaultRequestHandlerInvokerFactory;
import com.astamuse.asta4d.web.dispatch.DispatcherRuleExtractor;
import com.astamuse.asta4d.web.dispatch.RequestHandlerInvokerFactory;
import com.astamuse.asta4d.web.dispatch.mapping.UrlMappingRuleInitializer;
import com.astamuse.asta4d.web.util.GlobalRenderingInterceptor;
import com.astamuse.asta4d.web.util.bean.DeclareInstanceResolver;

public class WebApplicationConfiguration extends Configuration {

    private String flashScopeForwardParameterName = "flash_scope_id";

    private RequestHandlerInvokerFactory requestHandlerInvokerFactory;

    private List<DeclareInstanceResolver> instanceResolverList = new ArrayList<>();

    private DispatcherRuleExtractor ruleExtractor = new AntPathRuleExtractor();

    private UrlMappingRuleInitializer urlMappingRuleInitializer = null;

    public WebApplicationConfiguration() {
        this.setTemplateResolver(new WebApplicationTemplateResolver());
        this.setContextDataFinder(new WebApplicationContextDataFinder());
        this.setRequestHandlerInvokerFactory(new DefaultRequestHandlerInvokerFactory());
        this.setPageInterceptorList(createDefaultPageInterceptorList());

        // we only allow request scope being reversely injected
        List<String> reverseInjectableScopes = new ArrayList<>();
        reverseInjectableScopes.add(WebApplicationContext.SCOPE_REQUEST);
        this.setReverseInjectableScopes(reverseInjectableScopes);

    }

    protected List<PageInterceptor> createDefaultPageInterceptorList() {
        // afford a convenience for global rendering by default
        List<PageInterceptor> pageInterceptorList = new LinkedList<>();
        pageInterceptorList.add(new GlobalRenderingInterceptor());
        return pageInterceptorList;
    }

    @Override
    public void setPageInterceptorList(List<PageInterceptor> pageInterceptorList) {
        List<PageInterceptor> list = createDefaultPageInterceptorList();
        list.addAll(pageInterceptorList);
        super.setPageInterceptorList(list);
    }

    public final static WebApplicationConfiguration getWebApplicationConfiguration() {
        return (WebApplicationConfiguration) getConfiguration();
    }

    public String getFlashScopeForwardParameterName() {
        return flashScopeForwardParameterName;
    }

    public void setFlashScopeForwardParameterName(String flashScopeForwardParameterName) {
        this.flashScopeForwardParameterName = flashScopeForwardParameterName;
    }

    public RequestHandlerInvokerFactory getRequestHandlerInvokerFactory() {
        return requestHandlerInvokerFactory;
    }

    public void setRequestHandlerInvokerFactory(RequestHandlerInvokerFactory requestHandlerInvokerFactory) {
        this.requestHandlerInvokerFactory = requestHandlerInvokerFactory;
    }

    public List<DeclareInstanceResolver> getInstanceResolverList() {
        return instanceResolverList;
    }

    public void setInstanceResolverList(List<DeclareInstanceResolver> instanceResolverList) {
        this.instanceResolverList = instanceResolverList;
    }

    public DispatcherRuleExtractor getRuleExtractor() {
        return ruleExtractor;
    }

    public void setRuleExtractor(DispatcherRuleExtractor ruleExtractor) {
        this.ruleExtractor = ruleExtractor;
    }

    public UrlMappingRuleInitializer getUrlMappingRuleInitializer() {
        return urlMappingRuleInitializer;
    }

    public void setUrlMappingRuleInitializer(UrlMappingRuleInitializer urlMappingRuleInitializer) {
        this.urlMappingRuleInitializer = urlMappingRuleInitializer;
    }

}
