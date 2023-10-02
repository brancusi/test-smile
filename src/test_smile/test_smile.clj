(ns test-smile.test-smile
  (:require
   [scicloj.ml.dataset :as ds]
   [scicloj.metamorph.core :as mm]
   [scicloj.metamorph.ml :as ml]
   [camel-snake-kebab.core :as csk]
   [tablecloth.pipeline :as tc-mm]
   [tablecloth.api :as tc]
   [scicloj.ml.smile.manifold]
   [scicloj.metamorph.ml.preprocessing]))

(comment

  (System/getProperty "java.library.path")

  (let [pinguins (-> (tc/dataset
                      "https://github.com/allisonhorst/palmerpenguins/raw/5b5891f01b52ae26ad8cb9755ec93672f49328a8/data/penguins_size.csv"
                      {:key-fn csk/->kebab-case-keyword}))
        pipe (mm/pipeline
              (tc-mm/drop-missing)
              (tc-mm/select-columns [:culmen-length-mm :culmen-depth-mm :flipper-length-mm :body-mass-g])
              (scicloj.metamorph.ml.preprocessing/std-scale :type/numerical {})
              {:metamorph/id :model}
              (ml/model {:model-type :smile.manifold/isomap
                         :args [4 5 false]}))
        fit-ctx (mm/fit-pipe pinguins pipe)]

    fit-ctx)


;;Keep from folding
  )


