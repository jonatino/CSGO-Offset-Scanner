/*
 *    Copyright 2016 Jonathan Beaudoin
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.github.jonatino.offsets;

import com.github.jonatino.misc.PatternScanner;
import com.github.jonatino.misc.Strings;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.github.jonatino.OffsetManager.clientModule;
import static com.github.jonatino.OffsetManager.engineModule;
import static com.github.jonatino.misc.PatternScanner.getAddressForPattern;
import static com.github.jonatino.netvars.NetVars.byName;

/**
 * Created by Jonathan on 11/13/2015.
 */
public final class Offsets {
	
	/**
	 * Client.dll offsets
	 */
	public static int m_dwRadarBase;
	public static int m_dwWeaponTable;
	public static int m_dwWeaponTableIndex;
	public static int m_dwInput;
	public static int m_dwGlowObject;
	public static int m_dwForceJump;
	public static int m_dwForceAttack;
	public static int m_dwGlobalVars;
	public static int m_dwViewMatrix;
	public static int m_dwEntityList;
	public static int m_dwLocalPlayer;
	public static int m_nFallbackPaintKit;
	public static int m_nFallbackSeed;
	public static int m_nFallbackStatTrak;
	public static int m_iEntityQuality;
	public static int m_flFallbackWear;
	public static int m_iItemDefinitionIndex;
	public static int m_OriginalOwnerXuidLow;
	public static int m_iItemIDHigh;
	public static int m_iAccountID = byName("DT_WeaponCSBase", "m_iAccountID");
	public static int iViewModelIndex;
	public static int iWorldModelIndex;
	public static int m_iWorldDroppedModelIndex;
	public static int m_hViewModel;
	public static int m_nModelIndex;
	/**
	 * Engine.dll offsets
	 */
	public static int m_dwClientState;
	public static int m_dwInGame;
	public static int m_dwMaxPlayer;
	public static int m_dwMapDirectory;
	public static int m_dwMapname;
	public static int m_dwPlayerInfo;
	public static int m_dwViewAngles;
	public static int m_dwEnginePosition;
	public static int m_flFlashMaxAlpha;
	public static int m_bCanReload;
	public static int m_bSendPacket;
	//public static int m_dwForceFullUpdate;
	public static int m_dwLocalPlayerIndex;
	public static int m_iTeamNum;
	public static int m_bDormant;
	public static int m_bMoveType;
	public static int m_iCrossHairID;
	public static int m_iShotsFired;
	public static int m_dwBoneMatrix;
	public static int m_vecVelocity;
	public static int m_vecPunch;
	public static int m_lifeState;
	public static int m_dwModel;
	public static int m_dwIndex;
	public static int m_vecViewOffset;
	public static int m_bIsScoped;
	public static int m_bSpotted;
	public static int m_hActiveWeapon;
	public static int m_iWeaponID;
	public static int m_fFlags;
	public static int m_iHealth;
	public static int m_flNextPrimaryAttack;
	public static int m_nTickBase;
	public static int m_vecOrigin;
	public static int m_iClip1;
	public static int m_iClip2;
	public static int m_hMyWeapons;
	
	public static void load() {
		/*
		  Client.dll offsets
		 */
		m_dwRadarBase = getAddressForPattern(clientModule(), 0x1, 0x0, PatternScanner.READ | PatternScanner.SUBTRACT, 0xA1, 0x00, 0x00, 0x00, 0x00, 0x8B, 0x0C, 0xB0, 0x8B, 0x01, 0xFF, 0x50, 0x00, 0x46, 0x3B, 0x35, 0x00, 0x00, 0x00, 0x00, 0x7C, 0xEA, 0x8B, 0x0D, 0x00, 0x00, 0x00, 0x00);
		m_dwWeaponTable = getAddressForPattern(clientModule(), 0x1, 0x0, PatternScanner.READ | PatternScanner.SUBTRACT, 0x39, 0x86, 0x00, 0x00, 0x00, 0x00, 0x74, 0x06, 0x89, 0x86, 0x0, 0x0, 0x0, 0x0, 0x8B, 0x86);
		m_dwWeaponTableIndex = getAddressForPattern(clientModule(), 0x2, 0x0, PatternScanner.READ, 0x39, 0x86, 0x00, 0x00, 0x00, 0x00, 0x74, 0x06, 0x89, 0x86, 0x00, 0x00, 0x00, 0x00, 0x8B, 0x86);
		m_dwInput = getAddressForPattern(clientModule(), 0x1, 0x0, PatternScanner.READ | PatternScanner.SUBTRACT, 0xB9, 0x00, 0x00, 0x00, 0x00, 0xF3, 0x0F, 0x11, 0x04, 0x24, 0xFF, 0x50, 0x10);
		m_dwGlowObject = getAddressForPattern(clientModule(), 0x1, 0x4, PatternScanner.READ | PatternScanner.SUBTRACT, 0xA1, 0x00, 0x00, 0x00, 0x00, 0xa8, 0x01, 0x75, 0x00, 0x0f, 0x57, 0xc0, 0xc7, 0x05);
		m_dwForceJump = getAddressForPattern(clientModule(), 0x2, 0x0, PatternScanner.READ | PatternScanner.SUBTRACT, 0x89, 0x0D, 0x00, 0x00, 0x00, 0x00, 0x8B, 0x0D, 0x00, 0x00, 0x00, 0x00, 0x8B, 0xF2, 0x8B, 0xC1, 0x83, 0xCE, 0x08);
		m_dwForceAttack = getAddressForPattern(clientModule(), 0x2, 0xC, PatternScanner.READ | PatternScanner.SUBTRACT, 0x89, 0x0D, 0x00, 0x00, 0x00, 0x00, 0x8B, 0x0D, 0x00, 0x00, 0x00, 0x00, 0x8B, 0xF2, 0x8B, 0xC1, 0x83, 0xCE, 0x04);
		m_dwViewMatrix = getAddressForPattern(clientModule(), 0x3, 0xb0, PatternScanner.READ | PatternScanner.SUBTRACT, 0x0F, 0x10, 0x05, 0x00, 0x00, 0x00, 0x00, 0x8D, 0x85, 0x00, 0x00, 0x00, 0x00, 0xB9);
		m_dwEntityList = getAddressForPattern(clientModule(), 0x1, 0x0, PatternScanner.READ | PatternScanner.SUBTRACT, 0xBB, 0x00, 0x00, 0x00, 0x00, 0x83, 0xFF, 0x01, 0x0F, 0x8C, 0x00, 0x00, 0x00, 0x00, 0x3B, 0xF8);
		m_dwLocalPlayer = getAddressForPattern(clientModule(), 0x1, 0x2C, PatternScanner.READ | PatternScanner.SUBTRACT, 0xA3, 0x00, 0x00, 0x00, 0x00, 0xC7, 0x05, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xE8, 0x00, 0x00, 0x00, 0x00, 0x59, 0xC3, 0x6A, 0x00);
		m_bCanReload = getAddressForPattern(clientModule(), 0x2, 0x0, PatternScanner.READ, 0x80, 0xB9, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0F, 0x85, 0x0, 0x0, 0x0, 0x0, 0xA1);
		m_bDormant = getAddressForPattern(clientModule(), 0x2, 0x0, PatternScanner.READ, 0x88, 0x9E, 0x0, 0x0, 0x0, 0x0, 0xE8, 0x0, 0x0, 0x0, 0x0, 0x53, 0x8D, 0x8E, 0x0, 0x0, 0x0, 0x0, 0xE8, 0x0, 0x0, 0x0, 0x0, 0x8B, 0x06, 0x8B, 0xCE, 0x53, 0xFF, 0x90, 0x0, 0x0, 0x0, 0x0, 0x8B, 0x46, 0x64, 0x0F, 0xB6, 0xCB, 0x5E, 0x5B, 0x66, 0x89, 0x0C, 0xC5, 0x0, 0x0, 0x0, 0x0, 0x5D, 0xC2, 0x04, 0x00);
		
		/*
		  Engine.dll offsets
		 */
		m_dwGlobalVars = getAddressForPattern(engineModule(), 0x1, 0x0, PatternScanner.READ | PatternScanner.SUBTRACT, 0x68, 0x0, 0x0, 0x0, 0x0, 0x68, 0x0, 0x0, 0x0, 0x0, 0xFF, 0x50, 0x08, 0x85, 0xC0);
		m_dwClientState = getAddressForPattern(engineModule(), 0x1, 0x0, PatternScanner.READ | PatternScanner.SUBTRACT, 0xA1, 0x00, 0x00, 0x00, 0x00, 0x33, 0xD2, 0x6A, 0x0, 0x6A, 0x0, 0x33, 0xC9, 0x89, 0xB0);
		m_dwInGame = getAddressForPattern(engineModule(), 0x2, 0x0, PatternScanner.READ, 0x83, 0xB9, 0x00, 0x00, 0x00, 0x00, 0x06, 0x0F, 0x94, 0xC0, 0xC3);
		m_dwMaxPlayer = getAddressForPattern(engineModule(), 0x7, 0x0, PatternScanner.READ, 0xA1, 0x00, 0x00, 0x00, 0x00, 0x8B, 0x80, 0x00, 0x00, 0x00, 0x00, 0xC3, 0xCC, 0xCC, 0xCC, 0xCC, 0x55, 0x8B, 0xEC, 0x8A, 0x45, 0x08);
		m_dwMapDirectory = getAddressForPattern(engineModule(), 0x1, 0x0, PatternScanner.READ, 0x05, 0x00, 0x00, 0x00, 0x00, 0xC3, 0xCC, 0xCC, 0xCC, 0xCC, 0xCC, 0xCC, 0xCC, 0x80, 0x3D);
		m_dwMapname = getAddressForPattern(engineModule(), 0x1, 0x0, PatternScanner.READ, 0x05, 0x00, 0x00, 0x00, 0x00, 0xC3, 0xCC, 0xCC, 0xCC, 0xCC, 0xCC, 0xCC, 0xCC, 0xA1, 0x00, 0x00, 0x00, 0x00);
		m_dwPlayerInfo = getAddressForPattern(engineModule(), 0x2, 0x0, PatternScanner.READ, 0x8B, 0x89, 0x00, 0x00, 0x00, 0x00, 0x85, 0xC9, 0x0F, 0x84, 0x00, 0x00, 0x00, 0x00, 0x8B, 0x01);
		m_dwViewAngles = getAddressForPattern(engineModule(), 0x4, 0x0, PatternScanner.READ, 0xF3, 0x0F, 0x11, 0x80, 0x00, 0x00, 0x00, 0x00, 0xD9, 0x46, 0x04, 0xD9, 0x05, 0x00, 0x00, 0x00, 0x00);
		m_dwEnginePosition = getAddressForPattern(engineModule(), 0x4, 0x0, PatternScanner.READ | PatternScanner.SUBTRACT, 0xF3, 0x0F, 0x11, 0x15, 0x00, 0x00, 0x00, 0x00, 0xF3, 0x0F, 0x11, 0x0D, 0x00, 0x00, 0x00, 0x00, 0xF3, 0x0F, 0x11, 0x05, 0x00, 0x00, 0x00, 0x00, 0xF3, 0x0F, 0x11, 0x3D, 0x00, 0x00, 0x00, 0x00);
		m_dwLocalPlayerIndex = getAddressForPattern(engineModule(), 0x2, 0x0, PatternScanner.READ, 0x8B, 0x80, 0x00, 0x00, 0x00, 0x00, 0x40, 0xC3);
		m_bSendPacket = getAddressForPattern(engineModule(), 0, 0, PatternScanner.SUBTRACT, 0x01, 0x8B, 0x01, 0x8B, 0x40, 0x10);
		//m_dwForceFullUpdate = PatternScanner.getAddressForPattern(OffsetManager.engineModule(), 0x3, 0, PatternScanner.READ | PatternScanner.SUBTRACT, 0xB0, 0xFF, 0xB7, 0x00, 0x00, 0x00, 0x00, 0xE8);
		
		m_fFlags = byName("DT_BasePlayer", "m_fFlags");
		m_iHealth = byName("DT_BasePlayer", "m_iHealth");
		m_vecViewOffset = byName("DT_BasePlayer", "m_vecViewOffset[0]");
		m_hActiveWeapon = byName("DT_BasePlayer", "m_hActiveWeapon");
		m_nTickBase = byName("DT_BasePlayer", "m_nTickBase");
		m_vecVelocity = byName("DT_BasePlayer", "m_vecVelocity[0]");
		m_lifeState = byName("DT_BasePlayer", "m_lifeState");
		
		m_flFlashMaxAlpha = byName("DT_CSPlayer", "m_flFlashMaxAlpha");
		m_iShotsFired = byName("DT_CSPlayer", "m_iShotsFired");
		m_bIsScoped = byName("DT_CSPlayer", "m_bIsScoped");
		
		m_hMyWeapons = byName("DT_CSPlayer", "m_hMyWeapons");
		
		
		m_flNextPrimaryAttack = byName("DT_BaseCombatWeapon", "m_flNextPrimaryAttack");
		m_iClip1 = byName("DT_BaseCombatWeapon", "m_iClip1");
		m_iClip2 = byName("DT_BaseCombatWeapon", "m_iClip2");
		
		m_bSpotted = byName("DT_BaseEntity", "m_bSpotted");
		m_vecOrigin = byName("DT_BaseEntity", "m_vecOrigin");
		m_iTeamNum = byName("DT_BaseEntity", "m_iTeamNum");
		
		m_vecPunch = byName("DT_BasePlayer", "m_aimPunchAngle");
		
		m_iWeaponID = byName("DT_WeaponCSBase", "m_fAccuracyPenalty") + 0x2C;
		
		m_dwBoneMatrix = byName("DT_BaseAnimating", "m_nForceBone") + 0x1c;
		
		m_iCrossHairID = byName("DT_CSPlayer", "m_bHasDefuser") + 0x5C;
		
		m_iAccountID = byName("DT_WeaponCSBase", "m_iAccountID");
		m_nFallbackPaintKit = byName("DT_WeaponCSBase", "m_nFallbackPaintKit");
		m_nFallbackSeed = byName("DT_WeaponCSBase", "m_nFallbackSeed");
		m_nFallbackStatTrak = byName("DT_WeaponCSBase", "m_nFallbackStatTrak");
		m_iEntityQuality = byName("DT_WeaponCSBase", "m_iEntityQuality");
		m_flFallbackWear = byName("DT_WeaponCSBase", "m_flFallbackWear");
		m_iItemDefinitionIndex = byName("DT_WeaponCSBase", "m_iItemDefinitionIndex");
		m_OriginalOwnerXuidLow = byName("DT_WeaponCSBase", "m_OriginalOwnerXuidLow");
		m_iItemIDHigh = byName("DT_WeaponCSBase", "m_iItemIDHigh");
		m_iAccountID = byName("DT_WeaponCSBase", "m_iAccountID");
		iViewModelIndex = byName("DT_WeaponCSBase", "m_iViewModelIndex");
		iWorldModelIndex = byName("DT_WeaponCSBase", "m_iWorldModelIndex");
		m_iWorldDroppedModelIndex = byName("DT_WeaponCSBase", "m_iWorldDroppedModelIndex");
		m_hViewModel = byName("DT_CSPlayer", "m_hViewModel[0]");
		m_nModelIndex = byName("DT_BaseViewModel", "m_nModelIndex");
		
		m_dwModel = 0x6C;
		m_dwIndex = 0x64;
		m_bMoveType = 0x258;
	}
	
	public static void dump() {
		List<String> text = new ArrayList<>();
		for (Field field : Offsets.class.getFields()) {
			text.add(field.getName() + " -> " + Strings.hex(getValue(field)));
		}
		try {
			Files.write(Paths.get("Offsets.txt"), text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static int getValue(Field field) {
		try {
			return (int) field.get(Offsets.class);
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return -1;
	}
	
}
