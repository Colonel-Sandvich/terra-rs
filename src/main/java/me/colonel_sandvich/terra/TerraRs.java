package me.colonel_sandvich.terra;

import jnr.ffi.LibraryLoader;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static java.lang.System.mapLibraryName;

public class TerraRs implements ModInitializer {
	public static final String MOD_ID = "terra-rs";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");

		String dylib = "double_input";
		System.setProperty("jnr.ffi.library.path", getLibraryPath(dylib));

		RustLib rlib = LibraryLoader.create(RustLib.class).load(dylib);
		int r = rlib.double_input(20);

		LOGGER.info("Result from rust double_input: {}", r);
	}

	public interface RustLib {
		int double_input(int i);
	}

	public static String getLibraryPath(String dylib) {
		File f = new File(TerraRs.class.getResource("/" + mapLibraryName(dylib)).getFile());
		return f.getParent();
	}
}

