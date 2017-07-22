#include <jni.h>
#include <string>
#include <sstream>
#include <iomanip>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_projeto_NativeConvert_calcular(
        JNIEnv* env,
        jobject,
        jfloat valor) {

    float num = valor * 3.5;
    std::ostringstream os ;
    os << std::fixed << std::setfill ('0') << std::setprecision (2) << num;
    std::string hello = os.str() ;

    return env->NewStringUTF(hello.c_str());
}
