/*
 * Copyright 2014 astamuse company,Ltd.
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
package com.astamuse.asta4d.web.form.flow.base;

import java.util.Locale;

import org.jsoup.nodes.Element;

import com.astamuse.asta4d.Context;
import com.astamuse.asta4d.data.ContextDataHolder;

abstract class DelatedContext extends Context {
    private Context context;

    public DelatedContext(Context context) {
        this.context = context;
    }

    public int hashCode() {
        return context.hashCode();
    }

    public void setCurrentRenderingElement(Element elem) {
        context.setCurrentRenderingElement(elem);
    }

    public Element getCurrentRenderingElement() {
        return context.getCurrentRenderingElement();
    }

    public void setData(String key, Object data) {
        context.setData(key, data);
    }

    public void setData(String scope, String key, Object data) {
        context.setData(scope, key, data);
    }

    protected abstract String convertKey(String scope, String key);

    public <T> T getData(String key) {
        return context.getData(key);
    }

    public <T> T getData(String scope, String key) {
        return context.getData(scope, convertKey(scope, key));
    }

    public <T> ContextDataHolder<T> getDataHolder(String key) {
        return context.getDataHolder(key);
    }

    public <T> ContextDataHolder<T> getDataHolder(String scope, String key) {
        return context.getDataHolder(scope, convertKey(scope, key));
    }

    public boolean equals(Object obj) {
        return context.equals(obj);
    }

    public Locale getCurrentLocale() {
        return context.getCurrentLocale();
    }

    public void setCurrentLocale(Locale locale) {
        context.setCurrentLocale(locale);
    }

    public void init() {
        context.init();
    }

    public void clear() {
        context.clear();
    }

    public Context clone() {
        return context.clone();
    }

    public String toString() {
        return context.toString();
    }

}
