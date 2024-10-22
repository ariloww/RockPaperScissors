package com.example.rockpaperscissors.storage;

import com.example.rockpaperscissors.model.GameSession;

import java.io.*;
import java.util.concurrent.ConcurrentHashMap;

public class FileStorage {

    private static final String STORAGE_DIR = "game_sessions";
    private static FileStorage instance;
    private final ConcurrentHashMap<String, GameSession> sessionCache = new ConcurrentHashMap<>();

    private FileStorage() {
        File dir = new File(STORAGE_DIR);
        if (!dir.exists()) {
            dir.mkdir();
        } else {
            loadAllSessions();
        }
    }

    public static synchronized FileStorage getInstance() {
        if (instance == null) {
            instance = new FileStorage();
        }
        return instance;
    }

    public synchronized void saveGameSession(GameSession session) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(STORAGE_DIR + File.separator + session.getSessionId()))) {
            oos.writeObject(session);
            sessionCache.put(session.getSessionId(), session);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized GameSession loadGameSession(String sessionId) {
        if (sessionCache.containsKey(sessionId)) {
            return sessionCache.get(sessionId);
        }
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(STORAGE_DIR + File.separator + sessionId))) {
            GameSession session = (GameSession) ois.readObject();
            sessionCache.put(sessionId, session);
            return session;
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    private void loadAllSessions() {
        File dir = new File(STORAGE_DIR);
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                String sessionId = file.getName();
                loadGameSession(sessionId);
            }
        }
    }
}