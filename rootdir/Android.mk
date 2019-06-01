LOCAL_PATH := $(call my-dir)

# root init scripts
include $(CLEAR_VARS)
LOCAL_MODULE       := init.nubia.touch.lcd.rc
LOCAL_MODULE_TAGS  := optional
LOCAL_MODULE_CLASS := ETC
LOCAL_SRC_FILES    := vendor/etc/init/hw/init.nubia.touch.lcd.rc
LOCAL_MODULE_PATH  := $(TARGET_OUT_VENDOR_ETC)/init/hw
include $(BUILD_PREBUILT)
