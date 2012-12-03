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
package com.android.ide.eclipse.adt.internal.refactorings.core;

import com.android.annotations.NonNull;
import com.android.ide.eclipse.adt.internal.editors.manifest.ManifestInfo;
import com.android.ide.eclipse.adt.internal.project.BaseProjectHelper;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.corext.refactoring.rename.RenamePackageProcessor;
import org.eclipse.ltk.core.refactoring.participants.RenameRefactoring;

/**
 * TODO: Test renaming a DIFFERENT package than the application package!
 */
@SuppressWarnings({"javadoc", "restriction"})
public class AndroidPackageRenameParticipantTest extends RefactoringTestBase {
    public void testRefactor1() throws Exception {
        renamePackage(
                TEST_PROJECT,
                false /*renameSubpackages*/,
                true /*updateReferences*/,
                "my.pkg.name",

                "CHANGES:\n" +
                "-------\n" +
                "* Rename package 'com.example.refactoringtest' to 'my.pkg.name'\n" +
                "\n" +
                "* MainActivity.java - /testRefactor1/src/com/example/refactoringtest/MainActivity.java\n" +
                "  @@ -3 +3\n" +
                "  + import com.example.refactoringtest.R;\n" +
                "  +\n" +
                "\n" +
                "\n" +
                "* activity_main.xml - /testRefactor1/res/layout/activity_main.xml\n" +
                "  @@ -33 +33\n" +
                "  -     <fragment android:name=\"com.example.refactoringtest.MyFragment\"/>\n" +
                "  +     <fragment android:name=\"my.pkg.name.MyFragment\"/>\n" +
                "\n" +
                "\n" +
                "* AndroidManifest.xml - /testRefactor1/AndroidManifest.xml\n" +
                "  @@ -16 +16\n" +
                "  -             android:name=\"com.example.refactoringtest.MainActivity\"\n" +
                "  +             android:name=\"my.pkg.name.MainActivity\"\n" +
                "  @@ -25 +25\n" +
                "  -             android:name=\".MainActivity2\"\n" +
                "  +             android:name=\"my.pkg.name.MainActivity2\"");
    }

    public void testRefactor1_noreferences() throws Exception {
        renamePackage(
                TEST_PROJECT,
                false /*renameSubpackages*/,
                false /*updateReferences*/,
                "my.pkg.name",

                "CHANGES:\n" +
                "-------\n" +
                "* Rename package 'com.example.refactoringtest' to 'my.pkg.name'");
    }

    public void testRefactor2() throws Exception {
        // Tests custom view handling
        renamePackage(
                TEST_PROJECT2,
                false /*renameSubpackages*/,
                true /*updateReferences*/,
                "my.pkg.name",

                "CHANGES:\n" +
                "-------\n" +
                "* Rename package 'com.example.refactoringtest' to 'my.pkg.name'\n" +
                "\n" +
                "* MainActivity.java - /testRefactor2/src/com/example/refactoringtest/MainActivity.java\n" +
                "  @@ -3 +3\n" +
                "  + import com.example.refactoringtest.R;\n" +
                "  +\n" +
                "\n" +
                "\n" +
                "* customviews.xml - /testRefactor2/res/layout/customviews.xml\n" +
                "  @@ -9 +9\n" +
                "  -     <com.example.refactoringtest.CustomView1\n" +
                "  +     <my.pkg.name.CustomView1\n" +
                "\n" +
                "\n" +
                "* activity_main.xml - /testRefactor2/res/layout/activity_main.xml\n" +
                "  @@ -33 +33\n" +
                "  -     <fragment android:name=\"com.example.refactoringtest.MyFragment\"/>\n" +
                "  +     <fragment android:name=\"my.pkg.name.MyFragment\"/>\n" +
                "\n" +
                "\n" +
                "* customviews.xml - /testRefactor2/res/layout-land/customviews.xml\n" +
                "  @@ -9 +9\n" +
                "  -     <com.example.refactoringtest.CustomView1\n" +
                "  +     <my.pkg.name.CustomView1\n" +
                "\n" +
                "\n" +
                "* AndroidManifest.xml - /testRefactor2/AndroidManifest.xml\n" +
                "  @@ -16 +16\n" +
                "  -             android:name=\"com.example.refactoringtest.MainActivity\"\n" +
                "  +             android:name=\"my.pkg.name.MainActivity\"\n" +
                "  @@ -25 +25\n" +
                "  -             android:name=\".MainActivity2\"\n" +
                "  +             android:name=\"my.pkg.name.MainActivity2\"");
    }

    public void testRefactor2_renamesub() throws Exception {
        // Tests custom view handling
        renamePackage(
                TEST_PROJECT2,
                true /*renameSubpackages*/,
                true /*updateReferences*/,
                "my.pkg.name",

                "CHANGES:\n" +
                "-------\n" +
                "* Rename package 'com.example.refactoringtest' and subpackages to 'my.pkg.name'\n" +
                "\n" +
                "* MainActivity.java - /testRefactor2_renamesub/src/com/example/refactoringtest/MainActivity.java\n" +
                "  @@ -3 +3\n" +
                "  + import com.example.refactoringtest.R;\n" +
                "  +\n" +
                "\n" +
                "\n" +
                "* customviews.xml - /testRefactor2_renamesub/res/layout/customviews.xml\n" +
                "  @@ -9 +9\n" +
                "  -     <com.example.refactoringtest.CustomView1\n" +
                "  +     <my.pkg.name.CustomView1\n" +
                "\n" +
                "\n" +
                "* activity_main.xml - /testRefactor2_renamesub/res/layout/activity_main.xml\n" +
                "  @@ -33 +33\n" +
                "  -     <fragment android:name=\"com.example.refactoringtest.MyFragment\"/>\n" +
                "  +     <fragment android:name=\"my.pkg.name.MyFragment\"/>\n" +
                "\n" +
                "\n" +
                "* customviews.xml - /testRefactor2_renamesub/res/layout-land/customviews.xml\n" +
                "  @@ -9 +9\n" +
                "  -     <com.example.refactoringtest.CustomView1\n" +
                "  +     <my.pkg.name.CustomView1\n" +
                "\n" +
                "\n" +
                "* AndroidManifest.xml - /testRefactor2_renamesub/AndroidManifest.xml\n" +
                "  @@ -16 +16\n" +
                "  -             android:name=\"com.example.refactoringtest.MainActivity\"\n" +
                "  +             android:name=\"my.pkg.name.MainActivity\"\n" +
                "  @@ -25 +25\n" +
                "  -             android:name=\".MainActivity2\"\n" +
                "  +             android:name=\"my.pkg.name.MainActivity2\"\n" +
                "\n" +
                "\n" +
                "* customviews.xml - /testRefactor2_renamesub/res/layout/customviews.xml\n" +
                "  @@ -15 +15\n" +
                "  -     <com.example.refactoringtest.subpackage.CustomView2\n" +
                "  +     <my.pkg.name.subpackage.CustomView2\n" +
                "\n" +
                "\n" +
                "* customviews.xml - /testRefactor2_renamesub/res/layout-land/customviews.xml\n" +
                "  @@ -15 +15\n" +
                "  -     <com.example.refactoringtest.subpackage.CustomView2\n" +
                "  +     <my.pkg.name.subpackage.CustomView2");
    }

    public void testRefactor2_renamesub_norefs() throws Exception {
        // Tests custom view handling
        renamePackage(
                TEST_PROJECT2,
                true /*renameSubpackages*/,
                false /*updateReferences*/,
                "my.pkg.name",

                "CHANGES:\n" +
                "-------\n" +
                "* Rename package 'com.example.refactoringtest' and subpackages to 'my.pkg.name'");
    }


    // ---- Test infrastructure ----

    protected void renamePackage(
            @NonNull Object[] testData,
            boolean renameSubpackages,
            boolean updateReferences,
            @NonNull String newName,
            @NonNull String expected) throws Exception {
        IProject project = createProject(testData);
        renamePackage(project, renameSubpackages, updateReferences, newName, expected);
    }

    protected void renamePackage(
            @NonNull IProject project,
            boolean renameSubpackages,
            boolean updateReferences,
            @NonNull String newName,
            @NonNull String expected) throws Exception {
        ManifestInfo info = ManifestInfo.get(project);
        String currentPackage = info.getPackage();
        assertNotNull(currentPackage);

        IPackageFragment pkgFragment = getPackageFragment(project, currentPackage);
        RenamePackageProcessor processor = new RenamePackageProcessor(pkgFragment);
        processor.setNewElementName(newName);
        processor.setRenameSubpackages(renameSubpackages);
        processor.setUpdateReferences(updateReferences);
        assertNotNull(processor);

        RenameRefactoring refactoring = new RenameRefactoring(processor);
        checkRefactoring(refactoring, expected);
    }

    private static IPackageFragment getPackageFragment(IProject project, String pkg)
            throws CoreException, JavaModelException {
        IPackageFragment pkgFragment = null;
        IJavaProject javaProject = BaseProjectHelper.getJavaProject(project);
        assertNotNull(javaProject);
        IPackageFragment[] fragments = javaProject.getPackageFragments();
        for (IPackageFragment fragment : fragments) {
            String name = fragment.getElementName();
            if (pkg.equals(name)) {
                pkgFragment = fragment;
                break;
            }
        }
        return pkgFragment;
    }
}
