# 📦 Setup PostgreSQL pour le projet

Ce guide décrit les étapes nécessaires pour installer PostgreSQL, créer la BD `school_map`, et (optionnellement) utiliser DBeaver pour interagir avec la base.

---

## 🐘 1. Installer PostgreSQL

🔗 Télécharger PostgreSQL pour **Windows** ici :  
👉 [https://www.postgresql.org/download/windows/](https://www.postgresql.org/download/windows/)

Pendant l'installation :
- Choisir un mot de passe pour l'utilisateur `postgres`
- Choisir un port (par défaut : `5432`)
- Laisser les autres paramètres par défaut si tu débutes

✅ Recommandation : utilise `postgres` comme **nom d'utilisateur** et **mot de passe**

---

## 🔐 2. Créer les identifiants

Lors de l'installation :
- **Nom d'utilisateur** : `postgres`
- **Mot de passe** : `postgres`
> 💡 Tu peux choisir un autre user/password, mais pense à bien le noter et le configurer ensuite dans `application.properties` du projet.

---

## 🛠️ 3. (Optionnel) Installer DBeaver

DBeaver est un client SQL graphique pour interagir facilement avec ta base PostgreSQL.

🔗 Télécharger DBeaver Community Edition ici :  
👉 [https://dbeaver.io/download/](https://dbeaver.io/download/)

---

## 🧩 4. Connexion à la base de données

On peut se connecter à la base `school_map` de deux façons :

---

### 🖥️ 1. Avec DBeaver (interface graphique)

- **Hôte** : `127.0.0.1`
- **Port** : `5432`
- **Nom de la base** : `postgres`
- **Utilisateur** : `postgres` (par défaut)
- **Mot de passe** : `postgres` (par défaut)

> 💡 Sélectionne "PostgreSQL" comme type de connexion, puis clique sur "Test Connection".

---

### 🧾 2. Avec la ligne de commande `psql`

```bash
psql -h 127.0.0.1 -U postgres
```

---

## 🗃️ 5. Créer la base de données `school_map`

Tu peux créer ta base avec :

- **DBeaver**
- ou **psql** (terminal PostgreSQL)

### 📜 Requête SQL :
```sql
CREATE DATABASE school_map;
```

> ### Dans le fichier `application.properties`, mettre l'addresse du serveur Postgres (généralement 127.0.0.1)