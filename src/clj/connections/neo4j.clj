(ns connections.neo4j
  (:require
    [clojurewerkz.neocons.rest :as nr]
    [clojurewerkz.neocons.rest.nodes :as nn]
    [clojurewerkz.neocons.rest.relationships :as nrl]
    [clojurewerkz.neocons.rest.cypher :as cy]))

; Connection details
(def conn (nr/connect "http://localhost:7475/db/data/"))

(defn cypher [query]
  (clojurewerkz.neocons.rest.cypher/tquery conn query))

; Very haxxy way of reading node id, takes cypher return value as an input
(defn read-id-from-cypher-entry [entry]
  (Integer/parseInt
    (last (clojure.string/split
      (get (second (first entry)) :self)
      #"/"))))

(defn read-name-from-cypher-entry [entry]
  (get-in (second (first entry)) [:data :name]))

(defn clean-search-results [results]
  (map
    #(hash-map
      :id (read-id-from-cypher-entry %)
      :name (read-name-from-cypher-entry %))
    results))

(defn search-entries-by-name [regex]
  (clean-search-results
    (cypher (str "MATCH (search) WHERE search.name=~'"regex"' RETURN search"))))

(defn get-entry-by-name [name]
  (cypher (str "MATCH (n) WHERE n.name='"name"' RETURN n")))

(defn get-node-by-id [id]
  (nn/get conn id))

(defn read-name-by-id [id]
  (get-in (get-node-by-id id) [:data :name]))

(defn get-node-connections-out [id]
  (nrl/outgoing-for conn (get-node-by-id id)))

(defn get-node-connections-in [id]
  (nrl/incoming-for conn (get-node-by-id id)))

(defn get-connections-out-by-name [name]
  (get-node-connections-out (read-id-from-cypher-entry (get-entry-by-name name))))

(defn get-connections-in-by-name [name]
  (get-node-connections-in (read-id-from-cypher-entry (get-entry-by-name name))))

