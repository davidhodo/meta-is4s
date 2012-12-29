DESCRIPTION = "William Woodall's Serial Library"
PR = "r0"

SRC_URI = "git://github.com/davidhodo/serial.git;tag=fuerte;protocol=git"
#SRC_URI = "file://serial.patch"

S = "${WORKDIR}/git"

# cmake.bbclass exports configure, compile and install tasks...wahoo!
inherit cmake

EXTRA_OECMAKE = "-DSERIAL_BUILD_WITHOUT_ROS=ON \
                 -DBUILD_NO_CLANG=ON \
                "

do_install() {
  install -d ${D}${libdir}		# creates the lib directory
  install -m 0755 ${S}/lib/libserial.a ${D}${libdir}/ 	#copies the appropriate files into the directory
}

do_install_append() {
    install -d ${STAGING_INCDIR}/include/serial/
    install -m 0755 ${S}/include/serial/serial.h ${STAGING_INCDIR}/include/serial/
    install -m 0755 ${S}/include/serial/v8stdint.h ${STAGING_INCDIR}/include/serial/
    install -d ${STAGING_LIBDIR}
    install -m 0755 ${S}/lib/libserial.a ${STAGING_LIBDIR}/
}
