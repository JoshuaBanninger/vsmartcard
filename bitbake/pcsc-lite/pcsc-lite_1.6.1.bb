DESCRIPTION = "PC/SC Lite smart card framework and applications"
HOMEPAGE = "http://pcsclite.alioth.debian.org/"
LICENSE = "BSD"

DEPENDS = "libusb1"
RDEPENDS_${PN} = "libusb1"

SRC_URI = "https://alioth.debian.org/frs/download.php/3298/pcsc-lite-${PV}.tar.bz2 \
           file://pcscd.init \
           file://pcsc-lite-1.6.1.patch;apply=yes"

inherit autotools update-rc.d

INITSCRIPT_NAME = "pcscd"
INITSCRIPT_PARAMS = "defaults"

EXTRA_OECONF = " --enable-libusb \
                 --disable-libhal \
                 --enable-usbdropdir=${libdir}/pcsc/drivers \
	"

do_install() {
	oe_runmake DESTDIR="${D}" install
	install -d "${D}/etc/init.d"
	install -m 755 "${WORKDIR}/pcscd.init" "${D}/etc/init.d/pcscd"
}

PACKAGES =+ "libpcsclite"

FILES_libpcsclite = "${libdir}/libpcsclite.so.*"

SRC_URI[md5sum] = "ed023be61feebfafce12e86075912695"
SRC_URI[sha256sum] = "7094e8aefbf62f46fbcc2da11865a9730675cdceb0b3663f03a65ce65eedc91c"