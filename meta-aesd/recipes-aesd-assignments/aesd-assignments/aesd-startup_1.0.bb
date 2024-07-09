# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# Unable to find any files that looked like license statements. Check the accompanying
# documentation and source headers and set LICENSE and LIC_FILES_CHKSUM accordingly.
#
# NOTE: LICENSE is being set to "CLOSED" to allow you to at least start building - if
# this is not accurate with respect to the licensing of the software being built (it
# will not be in most cases) you must specify the correct value before using this
# recipe for anything other than initial testing/development!
DESCRIPTION = "A custom startup script to be added to init.rc"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
DEPENDS = "aesd-assignments"
SRC_URI = "file://aesdsocket-start-stop"
#SRC_URI = "gitsm://git@github.com/haimts/aesd-assignments.git;protocol=ssh;branch=assignment-6-haimts"

# Modify these as desired
#PV = "1.0+git${SRCPV}"
#SRCREV = "0b650eb6f80e53b6b68f5bd6802b94b89dd6768d"

#S = "${WORKDIR}/aesd-startup"

#FILES:${PN} += "${bindir}/aesdsocket"
#TARGET_LDFLAGS = "-lpthread -lrt"

inherit autotools update-rc.d systemd

INITSCRIPT_NAME = "aesdsocket-start-stop"
INITSCRIPT_PARAMS = "defaults 99"
INITSCRIPT_PACKAGE = "${PN}"

# NOTE: this is a Makefile-only piece of software, so we cannot generate much of the
# recipe automatically - you will need to examine the Makefile yourself and ensure
# that the appropriate arguments are passed in.
do_configure () {
	:
}
do_compile () {
	:
}
do_install () {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/${INITSCRIPT_NAME} ${D}${sysconfdir}/init.d/${INITSCRIPT_NAME}
}

# Add the startup script to the init.rc
update-rc.d_class-target() {
    install -d ${D}${sysconfdir}/rc.d
    ln -sf ${sysconfdir}/init.d/${INITSCRIPT_NAME} ${D}${sysconfdir}/rc.d/S99${INITSCRIPT_NAME}
}

FILES_${PN} += "${sysconfdir}/init.d/${INITSCRIPT_NAME} ${sysconfdir}/rc.d/S99${INITSCRIPT_NAME}"
