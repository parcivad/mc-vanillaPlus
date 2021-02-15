# VanillaPlus
Ein Plugin mit verschiedenen Funktionen und Features für einen besseren vanilla Server. Dieses Plugin
kann für ein Minecraft Projekt genutzt werden um das Spiel zu verbessern.

[![Discord](https://img.shields.io/discord/690934524955197471?label=Discord&logo=discord)](https://discord.gg/MFhh5XEM2b)

## Ausführung/Nutzung
Das Plugin kann auf einem Spigot basiertem Minecraft Server der Version 1.16 genutzt werden!

Die passende Spigot Version kann hier heruntergeladen werden. [(Spigot-1.16.4)](https://cdn.getbukkit.org/spigot/spigot-1.16.4.jar)
Wenn der Server erstmal gestartet wurde kann die `vanillaPlus.jar` Datei in den Ordner Plugins gelegt werden und sollte sich beim nächsten reload/start öffnen.

## Features
Das Plugin hat keinen Hauptnutzen sondern erfüllt eher einzelne Bedürfnisse.
Die Features, bzw. Commands, sind folgende
```
/tpa {to/accept/denied} {player} 
/inv {player}                   
/ec {player}                     
/spawn                                            
/pos {save/name} {name}   
/lock {message/on/off} {reason}
/stats {player}
/booster
/seed
/commands {command} {on/off}
/home {set/remove}
/tempban {player} {reason} {time} {m/h}
/tempunban {player}
/help
```
Zudem läst sich in der `ServerConfig.yml` eine passende Motd/MotdLocked setzten die dem Spieler dann gezeigt wird. 

Jeder Befehl wird ebenfalls in der `ServerConfig.yml` eingetragen und kann nach belieben an oder aus geschalten werden.

```diff
- Änderungen in der Config werden nur gespeichert wenn der Server aus ist!
```

## Command-list
Für das Aus-/Anschalten mit dem command `/commands` sind folgende Befehle vorgesehen
```
  command:
    tpa
    inv
    ec
    spawn
    pos
    lock
    stats
    seed
    booster
    home
    bansystem
```
Das deaktivieren eines Befehls deaktiviert nicht seine Funktion!
Beispiel:
  Wenn ein Booster gesetzt ist funktioniert dieser weiterhin, aber der Befehl /booster ist nichtmehr erreichbar.
  Deshalb sollte die Befehle am anfang direkt mit `/commands` festgelegt werden.

## Booster
Der Booster ist eine Position die man setzt. Der Spieler wird dann entlang der y Koordinate nach oben geboostet. Bei der gesetzten Position
wird das Boosten beendet und dem Spieler wird eine Elytra gegeben die für einen Flug reicht. 

Die Elytra ist zudem gegen das Duplizieren geschützt


## Permissions
Das Plugin hat Folgende Permissions:
```
  server.settings:
    description: "A permission to edit Server configurations, like the lockmode"
  enderchest.open:
    description: "Let the Player open EnderChest from another Player"
  invsee:
    description: "Let the Player see into a Inventory from another Player"
  tablist.owner:
    description: "Shows the Player has the Owner in the Tablist"
  tablist.dev:
    description: "Shows the Player has the Dev in the Tablist"
  manage.ban:
    description: "Player can ban people"
  manage.unban:
    description: "Player can unban people"
  manage.commands:
    description: "Can manage active or disable commands"
  home.noWait:
    description: "Player has not to wait 5min"
```

### Code
Das Projekt basiert auf Maven (IDEA)


