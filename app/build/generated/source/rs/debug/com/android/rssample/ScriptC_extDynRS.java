/*
 * Copyright (C) 2011-2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * This file is auto-generated. DO NOT MODIFY!
 * The source Renderscript file: /home/ben/AndroidStudioProjects/RenderScript/app/src/main/rs/extDynRS.rs
 */

package com.android.rssample;

import android.os.Build;
import android.os.Process;
import java.lang.reflect.Field;
import android.renderscript.*;
import android.content.res.Resources;

/**
 * @hide
 */
public class ScriptC_extDynRS extends ScriptC {
    private static final String __rs_resource_name = "extdynrs";
    // Constructor
    public  ScriptC_extDynRS(RenderScript rs) {
        this(rs,
             rs.getApplicationContext().getResources(),
             rs.getApplicationContext().getResources().getIdentifier(
                 __rs_resource_name, "raw",
                 rs.getApplicationContext().getPackageName()));
    }

    public  ScriptC_extDynRS(RenderScript rs, Resources resources, int id) {
        super(rs, resources, id);
        mExportVar_minValue = 255;
        __U32 = Element.U32(rs);
        mExportVar_maxValue = 0;
        __F32 = Element.F32(rs);
        __U8_4 = Element.U8_4(rs);
    }

    private Element __F32;
    private Element __U32;
    private Element __U8_4;
    private FieldPacker __rs_fp_F32;
    private FieldPacker __rs_fp_U32;
    private final static int mExportVarIdx_minValue = 0;
    private long mExportVar_minValue;
    public synchronized void set_minValue(long v) {
        if (__rs_fp_U32!= null) {
            __rs_fp_U32.reset();
        } else {
            __rs_fp_U32 = new FieldPacker(4);
        }
        __rs_fp_U32.addU32(v);
        setVar(mExportVarIdx_minValue, __rs_fp_U32);
        mExportVar_minValue = v;
    }

    public long get_minValue() {
        return mExportVar_minValue;
    }

    public Script.FieldID getFieldID_minValue() {
        return createFieldID(mExportVarIdx_minValue, null);
    }

    private final static int mExportVarIdx_maxValue = 1;
    private long mExportVar_maxValue;
    public synchronized void set_maxValue(long v) {
        if (__rs_fp_U32!= null) {
            __rs_fp_U32.reset();
        } else {
            __rs_fp_U32 = new FieldPacker(4);
        }
        __rs_fp_U32.addU32(v);
        setVar(mExportVarIdx_maxValue, __rs_fp_U32);
        mExportVar_maxValue = v;
    }

    public long get_maxValue() {
        return mExportVar_maxValue;
    }

    public Script.FieldID getFieldID_maxValue() {
        return createFieldID(mExportVarIdx_maxValue, null);
    }

    private final static int mExportVarIdx_LUT = 2;
    private float[] mExportVar_LUT;
    public synchronized void set_LUT(float[] v) {
        mExportVar_LUT = v;
        FieldPacker fp = new FieldPacker(1024);
        for (int ct1 = 0; ct1 < 256; ct1++) {
            fp.addF32(v[ct1]);
        }

        int []__dimArr = new int[1];
        __dimArr[0] = 256;
        setVar(mExportVarIdx_LUT, fp, __F32, __dimArr);
    }

    public float[] get_LUT() {
        return mExportVar_LUT;
    }

    public Script.FieldID getFieldID_LUT() {
        return createFieldID(mExportVarIdx_LUT, null);
    }

    //private final static int mExportForEachIdx_root = 0;
    private final static int mExportForEachIdx_minAndMax = 1;
    public Script.KernelID getKernelID_minAndMax() {
        return createKernelID(mExportForEachIdx_minAndMax, 35, null, null);
    }

    public void forEach_minAndMax(Allocation ain, Allocation aout) {
        forEach_minAndMax(ain, aout, null);
    }

    public void forEach_minAndMax(Allocation ain, Allocation aout, Script.LaunchOptions sc) {
        // check ain
        if (!ain.getType().getElement().isCompatible(__U8_4)) {
            throw new RSRuntimeException("Type mismatch with U8_4!");
        }
        // check aout
        if (!aout.getType().getElement().isCompatible(__U8_4)) {
            throw new RSRuntimeException("Type mismatch with U8_4!");
        }
        Type t0, t1;        // Verify dimensions
        t0 = ain.getType();
        t1 = aout.getType();
        if ((t0.getCount() != t1.getCount()) ||
            (t0.getX() != t1.getX()) ||
            (t0.getY() != t1.getY()) ||
            (t0.getZ() != t1.getZ()) ||
            (t0.hasFaces()   != t1.hasFaces()) ||
            (t0.hasMipmaps() != t1.hasMipmaps())) {
            throw new RSRuntimeException("Dimension mismatch between parameters ain and aout!");
        }

        forEach(mExportForEachIdx_minAndMax, ain, aout, null, sc);
    }

    private final static int mExportForEachIdx_transformation = 2;
    public Script.KernelID getKernelID_transformation() {
        return createKernelID(mExportForEachIdx_transformation, 35, null, null);
    }

    public void forEach_transformation(Allocation ain, Allocation aout) {
        forEach_transformation(ain, aout, null);
    }

    public void forEach_transformation(Allocation ain, Allocation aout, Script.LaunchOptions sc) {
        // check ain
        if (!ain.getType().getElement().isCompatible(__U8_4)) {
            throw new RSRuntimeException("Type mismatch with U8_4!");
        }
        // check aout
        if (!aout.getType().getElement().isCompatible(__U8_4)) {
            throw new RSRuntimeException("Type mismatch with U8_4!");
        }
        Type t0, t1;        // Verify dimensions
        t0 = ain.getType();
        t1 = aout.getType();
        if ((t0.getCount() != t1.getCount()) ||
            (t0.getX() != t1.getX()) ||
            (t0.getY() != t1.getY()) ||
            (t0.getZ() != t1.getZ()) ||
            (t0.hasFaces()   != t1.hasFaces()) ||
            (t0.hasMipmaps() != t1.hasMipmaps())) {
            throw new RSRuntimeException("Dimension mismatch between parameters ain and aout!");
        }

        forEach(mExportForEachIdx_transformation, ain, aout, null, sc);
    }

    private final static int mExportFuncIdx_changeLUT = 0;
    public void invoke_changeLUT() {
        invoke(mExportFuncIdx_changeLUT);
    }

    private final static int mExportFuncIdx_initLUT = 1;
    public void invoke_initLUT() {
        invoke(mExportFuncIdx_initLUT);
    }

    private final static int mExportFuncIdx_initMinAndMax = 2;
    public void invoke_initMinAndMax() {
        invoke(mExportFuncIdx_initMinAndMax);
    }

}

