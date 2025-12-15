package jp.co.mayekawa.util;

import java.util.Map;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.node.ArrayNode;
import tools.jackson.databind.node.ObjectNode;
import tools.jackson.databind.node.StringNode;

/**
 * Jackson のツリーモデル ({@link JsonNode}) を再帰的に走査し、 {@code null}（および
 * {@link com.fasterxml.jackson.databind.node.NullNode}）を 空文字
 * {@code ""}（{@link TextNode}）へ置換するユーティリティ。
 *
 * <p>
 * 本ユーティリティは、レスポンスJSONで {@code null} を返さずに空文字として返したい、
 * というAPI仕様に対応するための「最終出力整形」を目的とする。
 * </p>
 *
 * <h2>重要な注意</h2>
 * <ul>
 * <li>この処理は JSON の型を変える可能性がある。 例：本来数値/配列として扱われるプロパティが {@code null}
 * の場合、{@code ""}（文字列）になる。</li>
 * <li>対象の {@link JsonNode} を<strong>破壊的に更新</strong>する（ObjectNode/ArrayNode を直接
 * {@code set} で書き換える）。</li>
 * <li>入力が {@code null} の場合も {@code ""} の {@link TextNode} を返す。</li>
 * </ul>
 *
 * <p>
 * 上記の通り、クライアント側が厳密な型を期待する場合は互換性問題になり得るため、 利用範囲は「レスポンスの最終段」に限定することが望ましい。
 * </p>
 */
public final class NullToEmptyJson {

    /**
     * ユーティリティクラスのためインスタンス化を禁止する。
     */
    private NullToEmptyJson() {
    }

    /**
     * 指定した {@link JsonNode} を再帰的に走査し、以下の置換を行う。
     * <ul>
     * <li>{@code null} または {@link JsonNode#isNull()} のノードは
     * {@code ""}（{@link TextNode}）に置換する。</li>
     * <li>{@link ObjectNode} は全プロパティを走査し、値ノードを再帰的に置換する。</li>
     * <li>{@link ArrayNode} は全要素を走査し、要素ノードを再帰的に置換する。</li>
     * <li>それ以外（数値・文字列・真偽値など）はそのまま返す。</li>
     * </ul>
     *
     * @param node 置換対象の {@link JsonNode}（{@code null} 可）
     * @return 置換後の {@link JsonNode}（入力の型に応じて同一インスタンスを返す場合がある）
     */
    public static JsonNode replaceNullWithEmptyString(JsonNode node) {
        if (node == null || node.isNull()) {
            return StringNode.valueOf("");
        }
        if (node.isObject()) {
            ObjectNode obj = (ObjectNode) node;
            for (Map.Entry<String, JsonNode> e : obj.properties()) {
                obj.set(e.getKey(), replaceNullWithEmptyString(e.getValue()));
            }
            return obj;
        }
        if (node.isArray()) {
            ArrayNode arr = (ArrayNode) node;
            for (int i = 0; i < arr.size(); i++) {
                arr.set(i, replaceNullWithEmptyString(arr.get(i)));
            }
            return arr;
        }
        return node;
    }
}
