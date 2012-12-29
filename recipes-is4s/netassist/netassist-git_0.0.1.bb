DESCRIPTION = "Gavlab's Network Assisted GPS Library"
PR = "r0"

DEPENDS = "serial-git "
DEPENDS += "ublox-git "
DEPENDS += "ncurses "
DEPENDS += "protobuf "


SRC_URI = "https://github.com/GAVLab/network_gps.git;tag=master;protocol=git"
S = "${WORKDIR}"

# cmake.bbclass exports configure, compile and install tasks...wahoo!
inherit cmake

EXTRA_OECMAKE = "-Dublox_INCLUDE_DIRS=${STAGING_INCDIR}/include/ \
	             -Dublox_LIBRARIES=${STAGING_LIBDIR}/libublox.a \
				 -Dserial_INCLUDE_DIRS=${STAGING_INCDIR}/include/ \
		         -Dserial_LIBRARIES=${STAGING_LIBDIR}/libserial.a \	             
                 -DBUILD_WITH_ROS=OFF \
                 -DBUILD_NO_CLANG=ON \
                 -DUBLOX_BUILD_EXAMPLES=ON \
                "

do_install() {
  install -d ${D}${bindir}
  install -d ${D}${libdir}
  install -m 0755 ${S}/lib/libdata_dissemination.a ${D}${libdir}/
  install -m 0755 ${S}/bin/net_assist ${D}${bindir}/
}
