package com.wk.dialogutils;

public enum StackingBehavior {
    /**
     * The action buttons are always stacked vertically.
     * 操作按钮始终垂直堆叠。
     */
    ALWAYS,
    /**
     * The action buttons are stacked vertically IF it is necessary for them to fit in the dialog.
     * 如果需要将操作按钮放入对话框中，则将其垂直堆叠。
     */
    ADAPTIVE,
    /**
     * The action buttons are never stacked, even if they should be.
     * 动作按钮从不堆叠，即使它们应该堆叠。
     */
    NEVER
}
