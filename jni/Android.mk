LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := game
LOCAL_SRC_FILES := game.cpp

include $(BUILD_SHARED_LIBRARY)
