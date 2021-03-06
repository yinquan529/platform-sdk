/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Eclipse Public License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.eclipse.org/org/documents/epl-v10.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.ide.eclipse.adt.internal.wizards.templates;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateModelException;

import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;

@SuppressWarnings("javadoc")
public class FmEscapeXmlStringMethodTest extends TestCase {
    @SuppressWarnings("rawtypes")
    private void check(String s, String expected) throws TemplateModelException {
        FmEscapeXmlStringMethod method = new FmEscapeXmlStringMethod();
        List list = Collections.singletonList(new SimpleScalar(s));
        assertEquals(expected, ((SimpleScalar) method.exec(list)).getAsString());
    }

    public void test1() throws Exception {
        check("", "");
    }

    public void test2() throws Exception {
        check("foo", "foo");
    }

    public void test3() throws Exception {
        check(" Foo Bar ", "\" Foo Bar \"");
    }

    public void test4() throws Exception {
        check("@foo", "\\@foo");
    }

    public void test5() throws Exception {
        check("Hello\nWorld", "Hello\\nWorld");
    }

    public void test6() throws Exception {
        check("A & B", "A &amp; B");
    }

    public void test7() throws Exception {
        check("Foo's Bar", "Foo\\'s Bar");
    }

    public void test8() throws Exception {
        check("'\"\\", "\\'\\\"\\\\");
    }
}
