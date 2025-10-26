# Guidelines and Engineering Best Practice (Junie)

- https://www.jetbrains.com/junie/

## Core Engineering Principles
- **SOLID:**
  - **Single Responsibility:** Each class/module should have one reason to change. Split responsibilities into focused types.
  - **Open/Closed:** Prefer adding new implementations over modifying stable ones. Use interfaces and composition to extend behavior.
  - **Liskov Substitution:** Subtypes must be usable anywhere their base type is expected. Avoid strengthening preconditions or weakening postconditions.
  - **Interface Segregation:** Prefer small, cohesive interfaces tailored to clients.
  - **Dependency Inversion:** High-level modules depend on abstractions. Pass interfaces or functional types rather than concrete classes.
- **KISS:** Keep solutions as simple as possible, minimize cleverness.
- **YAGNI:** Don’t build features until they’re needed.
- **DRY:** Avoid duplication; extract reusable utilities when duplication appears twice.
- **Fail Fast:** Validate early and throw meaningful exceptions on invalid state.

## Architecture and Structure
- **Modules:** Keep logic in the closest relevant module. Shared functionality belongs in logchange-utils. Domain and generation logic live in the logchange-core. Keep CLI/plugins thin and delegating to logchange-commands.
- **Public API:** Minimize surface area. Keep classes/methods package-private unless truly needed externally.
- **Immutability:** Prefer immutable value objects. Use final fields and defensive copies where applicable.
- **Configuration:** Centralize config parsing/validation. Provide sensible defaults. Make behavior explicit and documented.

## Coding Standards
- **Language:** Java (consistent with project target). Use modern language features when they clearly improve readability and safety.
- **Naming:** Use descriptive names. For tests, use GivenWhenThen in method names.
- **Null-safety:** Avoid returning null. Prefer Optional for optional values; validate inputs with clear exceptions.
- **Exceptions:** Throw specific checked/unchecked exceptions as appropriate. Never swallow exceptions; add context and rethrow. Do not use exceptions for normal control flow.
- **Collections:** Preserve insertion order when it matters for changelog determinism. Use unmodifiable views when exposing collections.
- **Concurrency:** Avoid shared mutable state. If needed, document threading assumptions and use proper synchronization.

## Logging
- Use the project logger: dev.logchange.utils.logger.LogchangeLogger with LoggerLevel.
- Levels
  - ERROR: Failures that abort the operation; include actionable context.
  - WARN: Suspicious or deprecated usage that does not abort.
  - INFO: High-level progress (start/finish of commands, key decisions).
  - DEBUG: Detailed diagnostics helpful for troubleshooting; keep noise controlled.
- Do not log secrets, tokens, or personal data. Redact sensitive paths when necessary.
- Prefer structured, consistent messages. Include identifiers (e.g., version, file paths) to ease debugging.
- Do not use System.out/err directly in production code; tests may capture System.out when verifying CLI output.

## Error Handling
- Validate inputs at boundaries; return useful messages for CLI users and plugin users.
- Add context when rethrowing (include file path, template name, or config key).
- For user-facing commands, convert low-level exceptions to clear messages and non-zero exit codes.

## Testing Philosophy (Use TDD)
- Follow the TDD loop:
  1. Red: Write a failing test describing desired behavior.
  2. Green: Implement the simplest code to pass the test.
  3. Refactor: Improve design while tests stay green.
- Testing priorities: correctness, determinism, readability, speed.
- Tests should be deterministic and hermetic. Avoid external network and time-of-day dependencies. Control time via injected clocks when needed.

## Test Structure and Format
- Frameworks: JUnit 5 (Jupiter). Use Mockito (or hand-rolled fakes) for collaborators.
- Naming
  - Class names: <ClassUnderTest>Test or <Feature>IntegrationTest
  - Method names: given_<context>_when_<action>_then_<outcome>
- Given-When-Then layout within tests; keep clear separation with blank lines/comments.
```java
class SomeTest {

    @Test
    void given_OrderWithItem_when_CalculateTotal_then_ReturnsTotal() {
        // given: 
        Order order = new Order();
        order.addItem(new OrderItem("item1", 10));

        // when:
        int total = service.calculateTotal(order);

        // then: 
        assertThat(total).isEqualTo(25);
    }
}
```
- Assertions: Prefer expressive assertions. Assert full outputs (e.g., generated CHANGELOG content) using golden files only when necessary and stable.
- Coverage: Focus on meaningful scenarios and edge cases (empty/unreleased entries, malformed YAML, missing templates, conflicting versions, ordering rules).
- Unit vs Integration
  - Unit tests: exercise one class or small unit with collaborators mocked/faked.
  - Integration tests: verify end-to-end flows (e.g., generating changelog from sample directories and templates). Place inputs in src/test/resources and compare to expected outputs (e.g., expected-CHANGELOG.md).
- Test Data
  - Keep fixtures minimal and documented. Reuse helpers for building test objects.
  - When comparing files, normalize line endings and whitespace as the production code expects.

## Security and Compliance
- Never commit secrets. Respect .gitignore.
- Validate and sanitize external inputs (YAML, templates, CLI args).
- Use safe defaults; avoid executing untrusted templates beyond supported features.

## Documentation
- Update README or module-level docs for new features or flags.
- Javadoc public APIs and non-obvious logic. Keep comments current and value-adding.

## Changelog
- All user-visible changes should be captured via logchange entries (YAML in changelog/unreleased). Keep entries small, descriptive, and categorized.
```yaml
# This file is used by logchange tool to generate CHANGELOG.md 🌳 🪓 => 🪵 
# Visit https://github.com/logchange/logchange and leave a star 🌟 
# More info about configuration you can find https://github.com/logchange/logchange#yaml-format ⬅️⬅ ️
title: "Fixed problem with empty links section"
authors:
  - name: Junie by JetBrains
    nick: jetbrains-junie
    url: https://github.com/jetbrains-junie
type: fixed # available: [added/changed/deprecated/removed/fixed/security/dependency_update/other]
merge_requests:
  - 588 # Number of a pull request introducing this change 
issues:
  - 548 # Number of an issue describing the problem
```

## Dependency Management
- Prefer well-maintained, minimal dependencies. Avoid tight coupling to implementation details.
- Keep versions consistent across modules via the parent POM. Remove unused dependencies.

## Backward Compatibility
- When changing behavior that may break users, don't do that!
- Provide deprecation paths and WARN logs before removal when possible.

## Self-code review
After finishing pull requests, review it by creating a comment at GitHub answering to the following prompt:
```markdown
Conduct a thorough code review of Java code.
Focus on the following aspects:
- Compliance with Clean Code principles (readability, simplicity, avoiding repetition, good naming, and avoiding unnecessary logic).
- Adherence to SOLID principles and general OOP best practices.
- Project structure—correct use of layers, dependency injection, and configurations.
- Opportunities for refactoring and code simplification.
- Compliance with Java code style conventions and standards.
- Error and exception handling, data validation.
- Performance, security, and testability.

Finally, provide:
- A list of identified issues or areas for improvement (with a brief explanation).
- Suggestions for corrections or refactorings—specific code snippets with an example of a corrected solution.
- An overall code quality rating (1-10) with a brief justification.
```