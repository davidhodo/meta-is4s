DESCRIPTION = "Gavlab's uBlox Library"
PR = "r0"

DEPENDS = "serial-git "
DEPENDS += "boost "


SRC_URI = "git://github.com/GAVLab/ublox.git;tag=master;protocol=git"
S = "${WORKDIR}/git"

# cmake.bbclass exports configure, compile and install tasks...wahoo!
inherit cmake

EXTRA_OECMAKE = "-Dserial_INCLUDE_DIRS=${STAGING_INCDIR}/include/ \
		             -Dserial_LIBRARIES=${STAGING_LIBDIR}/libserial.a \
                 -DBUILD_WITH_ROS=OFF \
                 -DBUILD_NO_CLANG=ON \
                 -DUBLOX_BUILD_EXAMPLES=ON \
                "

do_install() {
	install -d ${STAGING_INCDIR}/include/ublox/
	install -m 0755 ${S}/include/ublox/ublox.h ${STAGING_INCDIR}/include/ublox/
	install -m 0755 ${S}/include/ublox/ublox_structures.h ${STAGING_INCDIR}/include/ublox/
	install -d ${D}${bindir}
	install -d ${D}${libdir}
	install -m 0755 ${S}/lib/libublox.a ${D}${libdir}/
	install -m 0755 ${S}/bin/assist_example ${D}${bindir}/
}
