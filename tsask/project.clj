(defproject csvexporter "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-cljsbuild "0.3.2"] 
            [lein-ring "0.8.5"]]
  :dependencies [[ring/ring-core "1.1.8"]
                 [ring/ring-jetty-adapter "1.1.8"]
                 [ring-mock "0.1.5"]
                 [ring/ring-json "0.2.0"]
                 [me.raynes/laser "1.1.1"]
                 [hiccup "1.0.3"]
                 [compojure "1.1.5"]
                 [mysql/mysql-connector-java "5.1.25"]
                 [org.clojure/java.jdbc "0.3.0-alpha4"]
                 [org.clojure/clojure "1.5.1"]
                 [enfocus "2.0.0-beta1"]]
  :source-paths ["src/clj"]
  :ring {:handler csvexporter.core/app
         :auto-reload? true
         :nrepl {:start? true :port 7000}}
  :main csvexporter.core
  :profiles {:uberjar {:aot :all}})
